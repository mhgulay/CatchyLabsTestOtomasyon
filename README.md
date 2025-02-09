# CatchyLabsTestOtomasyon Projesi

Bu proje, CatchyLabs platformu üzerinde otomasyon testleri gerçekleştirmek için oluşturulmuştur. Proje, Java programlama dili ve Maven yapılandırma aracı kullanılarak oluşturulmuştur. 

Testler, Cucumber framework'ü kullanılarak yazılmıştır.

## Proje Yapısı

- `src/test/java`: Test kodu.
- `src/test/resources`: Test kaynak dosyaları (feature dosyaları, yapılandırma dosyaları, vb.).

## Kullanılan Teknolojiler

- **Java**: Programlama dili (sürüm 11)
- **Maven**: Proje yönetim ve yapılandırma aracı (sürüm 3.8.1)
- **Cucumber**: BDD (Davranış Odaklı Geliştirme) test framework'ü (sürüm 7.8.0)
- **Selenium**: Web tarayıcı otomasyon aracı (sürüm 4.28.1)
- **JUnit**: Test framework'ü (sürüm 4.13.2)
- **Rest-Assured**: API test framework'ü (sürüm 5.3.0)
- **Log4j**: Loglama framework'ü (sürüm 1.2.17)
- **SLF4J**: Java için Basit Loglama Arabirimi (sürüm 2.0.9)

## Kurulum

Projeyi yerel makinenize klonlamak için aşağıdaki komutu kullanabilirsiniz:

```sh
git clone https://github.com/mhgulay/testinium-projesi.git
```
Yapılandırma
Proje yapılandırma dosyası config.json olarak adlandırılmıştır ve src/test/resources dizininde bulunmaktadır. Bu dosya, testlerin çalıştırılması için tarayıcı, URL, kullanıcı kimlik bilgileri ve mobil cihaz ayarlarını içerir.
config.json dosyasında kullanıcı adınızı ve şifrenizi güncelleyin.

Mobil-Web ya da headless modda çalıştırmak için config.json dosyasında mobile ve headless alanlarını güncelleyin.

```json
{
  "browser": "chrome",
  "url": "https://catchylabs-webclient.testinium.com/",
  "valid_credentials": {
    "username": "username",
    "password": "password."
  },
  "mobile": {
    "value": "none",
    "options": ["none", "iphone16", "samsungS24"]
  },
  "headless": {
    "value": "false",
    "options": ["true", "false"]
  }
}
```

## Testleri Çalıştırma

Testlerin Çalıştırılması
Tüm testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz:
```
mvn test
```
Testleri belirli bir tag'e göre çalıştırmak için aşağıdaki komutları kullanabilirsiniz:

```
mvn test -Dcucumber.filter.tags="@api" 
```
```
mvn test -Dcucumber.filter.tags="@ui"
```

## İçerik Bilgisi

### Pozitif Testler
#### API Testleri: Geçerli kimlik bilgileriyle başarılı giriş, toplama, çıkarma, çarpma ve bölme işlemleri gibi çeşitli senaryoları içerir.
#### Web UI Testleri: Geçerli tutarla para transferi, hesap adı düzenleme ve geçerli kart bilgileriyle para ekleme gibi senaryoları içerir.

### Negatif Testler
#### API Testleri: Geçersiz veri tipi kontrolü, boş değer kontrolü gibi senaryoları içerir.
#### Web UI Testleri: Geçersiz bilgilerle giriş, hesap adı düzenleme ve alanların boş bırakılması gibi senaryoları içerir.

### Katkıda Bulunma
Katkıda bulunmak isterseniz, lütfen bir pull request oluşturun veya bir issue açın. Her türlü katkı ve geri bildirim memnuniyetle karşılanır.