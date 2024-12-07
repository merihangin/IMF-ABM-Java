import java.util.ArrayList; 
import java.util.Random;


public class Simulation {
	
	//actors
	BoardMember [] executiveBoard;
	CountryBureaucrat bureaucracy;
	Government govt;
	IMFStaff staff;
	
	final int NUM_NEGOTIATED_CONDITIONALITY = 30;
	final int NUM_GOVERNMENT_PARTNERS = 1; //between 1-3
	final double AVG_VIEW_DIVERGENCE = 0.2; //between 0-1
	final double AVG_CONTENTIOUSNESS = 0.5; //between 0-1
	final double CONCERN_PROBABILITY = 0.4; //for EB members
	final double CRISIS_INTENSITY = 6; //between 0-6
	
	final double ACCEPTANCE_THRESHOLD = 0.9;
	final double REJECTION_THRESHOLD = 0.3;
	
	double governmentConsensusProbability;
	double pastPerformance;
	
	ArrayList<Conditionality> negotiatedConditionality;
	int numNegotiated;
	int numAccepted;
		
	
	public void run() {
		
		staff = new IMFStaff();
		
		executiveBoard = new BoardMember[24];
		Random generator1 = new Random();
		double totalConcern = 0;
		for ( int i = 0; i < 24; i++ ) {
			double concern = generator1.nextGaussian() * 0.1 + CONCERN_PROBABILITY;
			totalConcern += concern;
		}
		
		staff.legitimacyConcern = totalConcern/24;
			
		double bureaucraticPower = 0.2; //between 0.2-0.8
		bureaucraticPower += ( CRISIS_INTENSITY * 0.1 );
		
		bureaucracy = new CountryBureaucrat( bureaucraticPower );
		
		govt = new Government( NUM_GOVERNMENT_PARTNERS, AVG_VIEW_DIVERGENCE );
		
		
		
		Random generator = new Random();
		
		negotiatedConditionality = new ArrayList<Conditionality>( NUM_NEGOTIATED_CONDITIONALITY );
		for ( int i = 0; i < NUM_NEGOTIATED_CONDITIONALITY; i++ ) {	
			double cont = generator.nextGaussian() * 0.2 + AVG_CONTENTIOUSNESS;
			Conditionality c = new Conditionality();
			c.contentiousness = cont;
			c.acceptance = 0.5;
			negotiatedConditionality.add( c );
		}
		
		numNegotiated = 0;
		numAccepted = 0;
		
		
		for ( int j = 0; j < NUM_NEGOTIATED_CONDITIONALITY; j++ ) {
			Conditionality c = negotiatedConditionality.get( j );
			if ( negotiationOver( c ) ) {
				numNegotiated++;
				if ( c.accepted ) {
					numAccepted++;
				}
			}
		}
		
		//continue until decision is reached for each proposed conditionality
		while ( numNegotiated < NUM_NEGOTIATED_CONDITIONALITY ) {
			
			for ( int i = 0; i < NUM_NEGOTIATED_CONDITIONALITY; i++ ) {
				Conditionality c = negotiatedConditionality.get( i );
				if ( !negotiationOver( c ) ) {
					
					//negotiation between bureaucrats-government
					bureaucracy.negotiateWith( govt, c );
				
					//negotiation between IMF staff-bureaucrats
					bureaucracy.negotiateWith( staff, c );
					
					if ( negotiationOver( c ) ) {
						numNegotiated++;
					}
				}
			}
		}
		
		for ( int j = 0; j < NUM_NEGOTIATED_CONDITIONALITY; j++ ) {
			Conditionality c = negotiatedConditionality.get( j );
			if ( c.accepted ) {
				numAccepted++;
			}
		}
	}
	
	
	public boolean negotiationOver( Conditionality c ) {
		
		boolean over = true;
		
		if ( c.acceptance >= ACCEPTANCE_THRESHOLD ) {
			c.accepted = true;
		}
		else if ( c.acceptance <= REJECTION_THRESHOLD ) {
			c.accepted = false;
		}
		else {
			over = false;
		}
		
		return over;
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Simulation s = new Simulation();
		int totalAccepted = 0;
		
		for ( int i = 0; i < 1000; i++ ) { 
			s.run();
			totalAccepted += s.numAccepted;
		}
		
		
		
System.out.println( "Num partners \t view divergence \t contentiousness \t concern \t crisis \t conditionality");
		System.out.println( s.NUM_GOVERNMENT_PARTNERS +"\t"+ s.AVG_VIEW_DIVERGENCE + "\t" + s.AVG_CONTENTIOUSNESS + "\t" + s.CONCERN_PROBABILITY + "\t" + s.CRISIS_INTENSITY + "\t" + (totalAccepted/1000));
		
		
	}

}



public abstract class Agent {

	public abstract void negotiateWith( Agent a, Conditionality c );
	
}



public class IMFStaff extends Agent {
	
	double legitimacyConcern;

	public void negotiateWith( Agent a, Conditionality c ) {
		
	}
	
}


import java.util.ArrayList;


public class Government extends Agent {

	ArrayList<CoalitionPartner> governmentPartners;
	int numGovernmentPartners;
	double viewDivergence;
	
	public Government( int numGovernmentPartners, double viewDivergence ) {
		this.numGovernmentPartners = numGovernmentPartners;
		this.viewDivergence = viewDivergence;
		governmentPartners = new ArrayList<CoalitionPartner>( numGovernmentPartners );
	}

	@Override
	public void negotiateWith( Agent a, Conditionality c ) {
		
	}
	
}


public class CountryBureaucrat extends Agent {

	double power;
	static Random generator1;
	static Random generator2;
	
	public CountryBureaucrat( double power ) {
		this.power = power;
		generator1 = new Random();
		generator2 = new Random();
	}
	
	public void negotiateWith( Agent a, Conditionality c ) {
		if ( a instanceof IMFStaff  ) {
			double concern = ((IMFStaff) a).legitimacyConcern;
			double acceptance = generator1.nextGaussian() * 0.1 + concern;
			
			c.acceptance = c.acceptance + acceptance;
			
		}
		
		else if ( a instanceof Government ) {
			double governmentWeight = 1 - power;
			double contentiousness = c.contentiousness;
			double rejection;
			
			if ( ((Government) a).numGovernmentPartners == 1 ) {
				rejection = generator2.nextGaussian() * 0.1 + contentiousness;
			}
			else if ( ((Government) a).numGovernmentPartners == 2 ) {
				double rejection1 = generator2.nextGaussian() * 0.1 + contentiousness;
				double rejection2 = generator2.nextGaussian() * 0.1 + contentiousness - ( ( Government ) a ).viewDivergence;
				
				rejection = ( ( rejection1 + rejection2 ) / 2 );
			}
			else { //3 partners
				double rejection1 = generator2.nextGaussian() * 0.1 + contentiousness;
				double rejection2 = generator2.nextGaussian() * 0.1 + contentiousness - ( ( Government ) a ).viewDivergence;
				double rejection3 = generator2.nextGaussian() * 0.1 + contentiousness - ( ( Government ) a ).viewDivergence;
				
				rejection = ( ( rejection1 + rejection2 + rejection3 ) / 3 );
			}
			
			c.acceptance = c.acceptance - governmentWeight * rejection;
			
		}
		
	}
}



public class Conditionality {

	double contentiousness;
	double acceptance;
	boolean accepted;
}



public class CoalitionPartner extends Agent {

	public void negotiateWith( Agent a, Conditionality c ) {
		
	}
	
}



public class BoardMember {
	
	private int concernsRaised;
	
	public void setConcernsRaised( int concernsRaised ) {
		this.concernsRaised = concernsRaised;
	}
	
	public int getConcernsRaised() {
		return concernsRaised;
	}

} 