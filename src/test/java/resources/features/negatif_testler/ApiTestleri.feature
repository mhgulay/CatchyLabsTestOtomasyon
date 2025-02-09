Feature: API Testleri

  @api
  Scenario: Geçerli kimlik bilgileriyle başarılı giriş
    When kullanıcı giriş isteğini gönderir
    Then yanıt durum kodu 200 olmalıdır
    And yanıt bir access_token içermelidir
    And yanıt bir refresh_token içermelidir

  @api
  Scenario: Geçersiz veri tipi kontrolü
    Given "number1" değeri "abc" ve "number2" değeri 10 sayı olarak gönderilir
    When "Calculator-Multiply" API'sine istek gönder
    Then yanıt durum kodu 400 olmalıdır
    And hata mesajı "Invalid input type" olmalıdır

  @api
  Scenario: Boş değer kontrolü
    Given "number1" değeri " " boş ve "number2" değeri 10 sayı gönderilir
    When "Calculator-Multiply" API'sine istek gönder
    Then yanıt durum kodu 400 olmalıdır
    And hata mesajı "Input cannot be empty" olmalıdır

#  Scenario Outline: Geçersiz veri tipi kontrolü
#    Given "number1" değeri "<num_one>" ve "number2" değeri "<num_two>"
#    When "<operation>" API'sine istek gönder
#    Then yanıt durum kodu 400 olmalıdır
#    And hata mesajı "Invalid input type" olmalıdır
#    Examples:
#      | num_one | num_two | operation           |
#      | "abc"   | 10      | Calculator-Add      |
#      | 10      | "xyz"   | Calculator-Subtract |
#      | "   "   | 10      | Calculator-Multiply |
#      | null    | 20      | Calculator-Divide   |