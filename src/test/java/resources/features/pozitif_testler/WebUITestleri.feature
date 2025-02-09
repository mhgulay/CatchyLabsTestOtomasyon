Feature: Banka Girişi, Para Transferi ve Hesap Düzenleme İşlevselliği

  Background: Kullanıcı geçerli kimlik bilgileriyle başarılı giriş yapabilmelidir
    Given Giriş sayfasına gidiyorum
    Then Giriş sayfasını görebilmeliyim
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır

  @ui
  Scenario: Geçerli tutarla Testinium-1 hesabına para transferi işlevselliğini doğrula
    Then Hesap bakiye değeri alınır
    Then "Transfer money" butonuna tıklanır
    And Amount alanına "100" değeri girilir
    And "Send" butonuna tıklanır
#    Then "Transfer Başarılı" mesajı görüntülenir
    And 2 saniye beklenir
    And Yeni bakiye değeri alınır
    And Hesap Amount değerindeki azalmanın "200.00" olduğu kontrol edilir

  @ui
  Scenario Outline: Geçerli tutarla para transferi işlevselliğini doğrula
    Then Hesap bakiye değeri alınır
    Then "Transfer money" butonuna tıklanır
    And 1 saniye beklenir
    And "Receiver account" tıklanır
    And 1 saniye beklenir
    And "<accountName>" alıcı hesabı olarak seçilir
    And 1 saniye beklenir
    And Browser alert "Tamam" butonuna tıklanır
    And Amount alanına "100" değeri girilir
    And "Send" butonuna tıklanır
#    Then "Transfer Başarılı" mesajı görüntülenir
    And 2 saniye beklenir
    And Yeni bakiye değeri alınır
    And 5 saniye beklenir
    Then Transaction detayda "Sender:" "Test" kontrol edilir
    Then Transaction detayda "Receiver:" "<accountName>" kontrol edilir
    Then Transaction detayda "Time:" "işlem tarihi" kontrol edilir
    Then Transaction detayda "Amount:" "200.00" kontrol edilir
    And Hesap Amount değerindeki azalmanın "200.00" olduğu kontrol edilir

    Examples:
      | accountName |
      | Testinium-2 |
      | Testinium-3 |
      | Testinium-4 |
      | Testinium-5 |

  @ui
  Scenario: Hesap adını sadece harf içeren bir isimle düzenleme işlevselliğini doğrula
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve "Testinium" değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Update Başarılı" mesajı görüntülenir
    And 2 saniye beklenir
    And "Account name" alanında "Testinium" değeri olduğu kontrol edilir
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve "Test" değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Update Başarılı" mesajı görüntülenir

  @ui
  Scenario: Geçerli kart bilgileri ve tutarla para ekleme işlevselliğini doğrula
    Then Hesap bakiye değeri alınır
    Then "Add money" butonuna tıklanır
    And "Amount" input alanına "100" değeri girilir
    And "CVV" input alanına "110" değeri girilir
    And "Card holder" input alanına "Muhammet Hamdi Gülay" değeri girilir
    And "Card number" input alanına "1234 1234 1234 1234" değeri girilir
    And "Expiry date" input alanına "1026" değeri girilir
    And "Add" butonuna tıklanır
#    Then "Para Ekleme Başarılı" mesajı görüntülenir
    And 2 saniye beklenir
    And Yeni bakiye değeri alınır
#    And Hesap Amount değerindeki artışın "100.00" olduğu kontrol edilir



