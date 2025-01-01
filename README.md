
# Akademik Araştırma için Java Simülasyon Kodu

Bu depo, Uluslararası Para Fonu (IMF) programı müzakereleri bağlamında araştırma amaçları için kullanılan Java simülasyon kodunu içermektedir. Simülasyon, hükümet bürokratları, IMF teknik kadrosu ve koalisyon ortakları gibi farklı aktörler arasındaki etkileşimleri modelleyerek, müzakereler sırasında koşullulukların kabul edilmesi veya reddedilmesini incelemektedir.


## Amaç

Bu kod, IMF koşulluluğu müzakerelerinin dinamiklerini simüle etmek için tasarlanmıştır. Çeşitli aktörlerin rollerini inceler, bunlar:
- Hükümetler ve koalisyon ortakları 
- Ülke bürokratları 
- IMF teknik kadrosu
olarak kategorize edilmiştir.

Simülasyon, aşağıdaki gibi değişen parametreler altında kabul edilen veya reddedilen koşullulukların sayısına dair veriler üretir:
- Hükümetler içindeki görüş ayrılıkları
- Önerilen koşullulukların tartışmalı olması
- IMF teknik kadrosunun endişe seviyeleri 

## Dosyalar

Bu proje tek bir dosya olarak organize edilmiştir:
- **Simulation.java**: Ana simülasyon mantığını ve tüm destekleyici sınıfları(e.g., `Agent`, `IMFStaff`, `Government`, vb.) tek bir dosyada içerir.

## Çalıştırma

1. **Önkoşullar**:
   - Java Development Kit(JDK) 8 veya üstünü yükleyin. 
   - `javac` (Java compiler) ve `java` (Java runtime) araçlarının PATH'inizde mevcut olduğundan emin olun.

2. **Kodu Derleme**:
   - Bir terminal veya komut istemi açın.
   - `Simulation.java` dosyasını içeren dizine gidin.
   - Kodu derlemek için şu komutu kullanın:
     ```
     javac Simulation.java
     ```

3. **Simülasyonu Çalıştırma**:
   - Başarılı bir derleme sonrası, simülasyonu şu şekilde çalıştırın:
     ```
     java Simulation
     ```

4. **Çıktı**:
   - Program, simülasyon sonuçlarını, önemli parametreleri ve kabul edilen ortalama koşulluluk sayısını çıktı olarak verecektir. 

## Önemli Parametreler

Simülasyon, aşağıdaki parametreleri kullanır:
- `NUM_NEGOTIATED_CONDITIONALITY`: Önerilen toplam koşulluluk sayısı.
- `AVG_VIEW_DIVERGENCE`: Hükümet ortakları arasındaki ortalama görüş ayrılığı.
- `AVG_CONTENTIOUSNESS`: Önerilen koşullulukların ortalama tartışmalılığı.
- `CONCERN_PROBABILITY`: IMF teknik kadrosu tarafından endişelerin dile getirilme olasılığı.

Bu parametreler, sonuçları gözlemlemek için `Simulation` sınıfı içinde değiştirilebilir. 

## Atıf

Bu kodu akademik amaçlarla kullanıyorsanız, ilgili çalışmayı lütfen aşağıdaki şekilde alıntılayın:


> [Cilt adı]  
> [Merih Angın]  
> [Bölüm adı], [2025]

## Lisans


---

Soru veya geri bildirim için lütfen angin.merih@gmail.com adresi ile iletişime geçiniz.
