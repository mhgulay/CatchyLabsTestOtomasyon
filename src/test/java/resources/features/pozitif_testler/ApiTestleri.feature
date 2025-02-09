Feature: API Testleri
  @api
  Scenario: Geçerli kimlik bilgileriyle başarılı giriş
    When kullanıcı giriş isteğini gönderir
    Then yanıt durum kodu 200 olmalıdır
    And yanıt bir access_token içermelidir
    And yanıt bir refresh_token içermelidir

#  Scenario: Geçerli refresh token ile access token yenileme
#    When kullanıcı refresh token ile giriş yenileme isteğini gönderir
#    Then yanıt durum kodu 200 olmalıdır
#    And yanıt yeni bir access_token içermelidir
#    And yanıt yeni bir refresh_token içermelidir
#
#  # Refresh Token
#  Scenario: Refresh Token ile yeni access token alınabilir
#    Given Geçerli bir "refresh_token" ve "access_token" mevcut
#    When "POST Refresh Token" API'sine istek gönder
#    Then Yanıt olarak "user_id", "access_token" ve "refresh_token" alınmalıdır
#
#  # Start Session
#  Scenario: Kullanıcı oturum başlatabilir
#    Given Geçerli bir "access_token" mevcut
#    When "POST Start Session" API'sine istek gönder
#    Then Yanıt olarak oturum bilgisi içeren bir yanıt alınmalıdır
#
#  # End Session
#  Scenario: Kullanıcı oturumu sonlandırabilir
#    Given Geçerli bir "access_token" ve "session_id" mevcut
#    When "POST End Session" API'sine istek gönder
#    Then Yanıt alınmamalıdır
#

   #Hesaplama İşlemleri - Toplama
  @api
  Scenario Outline: Toplama işlemi yapılabilir
    Given "number1" değeri <num_1> ve "number2" değeri <num_2>
    When "Calculator-Add" API'sine istek gönder
    Then yanıt durum kodu 200 olmalıdır
    Then Yanıt "result": <value> olmalıdır

    Examples:
    # Temel İşlemler
      | num_1      | num_2     | value       |
      | 10         | 10        | 20.00       |
      | -10        | -10       | -20.00      |
      | 0          | 0         | 0.00        |

    # Ondalıklı Sayılar
      | 10.99      | 10.01     | 21.00       |
      | -10.95     | -10.05    | -21.00      |
      | 10.181818  | 10.181818 | 20.36       |

    # Sınır Değerleri
      | 999999999  | 1         | 1000000000  |
      | -999999999 | -1        | -1000000000 |

    # Farklı İşaretler
      | -10        | 20        | 10.00       |
      | 15         | -25       | -10.00      |

  # Hesaplama İşlemleri - Çıkarma
  @api
  Scenario Outline: Çıkarma işlemi yapılabilir
    Given "number1" değeri <num_1> ve "number2" değeri <num_2>
    When "Calculator-Subtract" API'sine istek gönder
    Then yanıt durum kodu 200 olmalıdır
    Then Yanıt "result": <value> olmalıdır
    Examples:
    # Temel İşlemler
      | num_1      | num_2    | value       |
      | 20         | 10       | 10.00       |
      | -10        | -20      | 10.00       |
      | 0          | 0        | 0.00        |

    # Ondalıklı Sayılar
      | 20.99      | 10.99    | 10.00       |
      | -20.95     | -10.95   | -10.00      |
      | 10.181818  | 5.181818 | 5.00        |

    # Sınır Değerleri
      | 999999999  | -1       | 1000000000  |
      | -999999999 | 1        | -1000000000 |

    # Özel Durumlar
      | 0          | 10       | -10.00      |
      | 10         | 0        | 10.00       |


  # Hesaplama İşlemleri - Çarpma
  @api
  Scenario Outline: Çarpma işlemi yapılabilir
    Given "number1" değeri <num_1> ve "number2" değeri <num_2>
    When "Calculator-Multiply" API'sine istek gönder
    Then yanıt durum kodu 200 olmalıdır
    Then Yanıt "result": <value> olmalıdır
    Examples:
    # Temel İşlemler
      | num_1     | num_2     | value     |
      | 10        | 10        | 100.00    |
      | -10       | -10       | 100.00    |
      | 0         | 0         | 0.00      |

    # Ondalıklı Sayılar
      | 10.99     | 10        | 109.90    |
      | -10.95    | -10       | 109.50    |
      | 10.181818 | 10.181818 | 103.67    |

    # Sınır Değerleri
      | 999999    | 1000      | 999999000 |
      | -999999   | -1000     | 999999000 |

    # Sıfır ile Çarpma
      | 0         | -10       | 0.00      |
      | 10        | 0         | 0.00      |


  # Hesaplama İşlemleri - Bölme
  @api
  Scenario Outline: Bölme işlemi yapılabilir
    Given "number1" değeri <num_1> ve "number2" değeri <num_2>
    When "Calculator-Divide" API'sine istek gönder
    Then yanıt durum kodu 200 olmalıdır
    Then Yanıt "result": <value> olmalıdır
    Examples:
    # Temel İşlemler
      | num_1      | num_2 | value     |
      | 100        | 10    | 10.00     |
      | -100       | -10   | 10.00     |
      | 100        | -10   | -10.00    |

    # Ondalıklı Sayılar
      | 10.99      | 2     | 5.50      |
      | -10.95     | -3    | 3.65      |
      | 10.181818  | 2     | 5.09      |

    # Hassas Bölmeler
      | 1          | 3     | 0.33      |
      | 2          | 3     | 0.67      |

    # Sınır Değerleri
      | 999999999  | 1     | 999999999 |
      | -999999999 | -1    | 999999999 |

  @api
  Scenario: Sıfıra bölme kontrolü
    Given "number1" değeri 10 ve "number2" değeri 0
    When "Calculator-Divide" API'sine istek gönder
    Then yanıt durum kodu 400 olmalıdır
    And hata mesajı "Division by zero is not allowed" olmalıdır

  @api
  Scenario Outline: Büyük sayı kontrolleri
    Given "number1" değeri <num_1> ve "number2" değeri <num_2>
    When "<operation>" API'sine istek gönder
    Then yanıt durum kodu 400 olmalıdır
    And hata mesajı "Number out of range" olmalıdır
    Examples:
      | operation           | num_1      | num_2  |
      | Calculator-Add      | 9999999999 | 1      |
      | Calculator-Multiply | 999999     | 999999 |

  @api
  Scenario Outline: İşlem performans kontrolü
    Given 1000 adet "<operation>" işlemi yapılacak
    When Her işlem için random sayılar ile API'ye istek gönderilir
    Then Tüm yanıtlar 200 olmalıdır
    And Ortalama yanıt süresi 500ms altında olmalıdır
    Examples:
      | operation           |
      | Calculator-Add      |
      | Calculator-Subtract |
      | Calculator-Multiply |
      | Calculator-Divide   |

  # Banka Hesabı - Hesap Oluşturma
#  Scenario: Yeni banka hesabı oluşturulabilir
#    Given Banka hesabı adı "account_name" ve tipi "CHECKING"
#    When "POST MoneyTransfer - CreateAccount" API'sine istek gönder
#    Then Yanıt olarak "bank_account_id" ve "balance" içeren bir yanıt alınmalıdır
#
#  # Banka Hesapları - Hesap Bilgisi
#  Scenario: Kullanıcı banka hesabı bilgilerini alabilir
#    Given Geçerli bir "account_id"
#    When "GET MoneyTransfer - GetAccount" API'sine istek gönder
#    Then Yanıt olarak banka hesabı bilgisi alınmalıdır
#
#  # Para Transferi - Para Gönderme
#  Scenario: Para transferi yapılabilir
#    Given Geçerli bir "sender_account_id" ve "receiver_account_id" mevcut
#    When "POST MoneyTransfer - Send Money" API'sine istek gönder
#    Then Yanıt olarak "transaction_id" ve "transaction_time" içeren bir yanıt alınmalıdır
#
#  # Banka Hesapları - Hesap İşlemleri
#  Scenario: Kullanıcı banka hesabı işlemlerini alabilir
#    Given Geçerli bir "account_id"
#    When "GET MoneyTransfer - GetAccountTransactions" API'sine istek gönder
#    Then Yanıt olarak işlem bilgisi alınmalıdır

#  Scenario: Ardışık işlem performans kontrolü
#    Given 1000 adet çarpma işlemi yapılacak
#    When Her işlem için random sayılar ile API'ye istek gönderilir
#    Then Tüm yanıtlar 200 olmalıdır
#    And Ortalama yanıt süresi 500ms altında olmalıdır