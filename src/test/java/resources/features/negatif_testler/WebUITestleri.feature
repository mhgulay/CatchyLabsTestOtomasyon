Feature: Banka Girişi, Para Transferi ve Hesap Düzenleme İşlevselliği

  Background: Login sayfası açılır
    Given Giriş sayfasına gidiyorum
    Then Giriş sayfasını görebilmeliyim

  @ui
  Scenario Outline: Geçersiz bilgiler ile giriş işlevselliğini doğrula
    When "Username" input alanına configten okunan "<username>" değeri yazılır
    When "Password" input alanına configten okunan "<password>" değeri yazılır
    And "Login" butonuna tıklanır
    Then "Username or Password Invalid!" ekranda görülmelidir
    Examples:
      | username         | password         |
      | valid_username   | invalid_password |
      | invalid_username | valid_password   |

  @ui
  Scenario: Hesap adını yalnızca rakam içeren bir isimle düzenleme işlevselliğini doğrula
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve "123456" değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Girilen değerde hata var" mesajı görüntülenir

  @ui
  Scenario: Hesap adını boş bırakarak düzenleme işlevselliğini doğrula
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve " " değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Girilen değerde hata var" mesajı görüntülenir

  @ui
  Scenario: Hesap adını geçersiz karakterler içeren bir isimle düzenleme işlevselliğini doğrula
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve ".*/;,..*)([]" değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Girilen değerde hata var" mesajı görüntülenir

  @ui
  Scenario: Hesap adını 20 karakterden uzun bir isimle düzenleme işlevselliğini doğrula
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır
    Then "Edit account" butonuna tıklanır
    And 1 saniye beklenir
    And "Account name" input alanı dolu ise temizlenir ve "TestiniumTestiniumTestinium" değeri girilir
    Then "UPDATE" butonuna tıklanır
#    Then "Girilen değerde hata var" mesajı görüntülenir

  @ui
  Scenario Outline: Alanlar boş bırakılır ve required mesajı kontrol edilir
    When "Username" input alanına configten okunan "valid_username" değeri yazılır
    When "Password" input alanına configten okunan "valid_password" değeri yazılır
    And "Login" butonuna tıklanır
    Then Anasayfa görüntülenmelidir
    When "Open Money Transfer" butonuna tıklanır
    Then "Add money" butonuna tıklanır
    And "Amount" input alanına "<amount>" değeri girilir
    And "CVV" input alanına "<cvv>" değeri girilir
    And "Card holder" input alanına "<holder>" değeri girilir
    And "Card number" input alanına "<number>" değeri girilir
    And "Expiry date" input alanına "<expiry>" değeri girilir
    Then "Required" mesajı görüntülenir
#    Then "Hata" mesajı görüntülenir
    Examples:
      | amount | cvv | holder | number              | expiry |
      |        | 110 | Test   | 1234 1234 1234 1234 | 12/26  |
      | 100    |     | Test   | 1234 1234 1234 1234 | 12/26  |
      | 100    | 110 |        | 1234 1234 1234 1234 | 12/26  |
      | 100    | 100 | Test   | 1234 1234 1234 1234 |        |
      | 100    | 100 | Test   |                     | 12/26  |

