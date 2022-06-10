### t_user

| column        |         type |           option            |        option        |
|:--------------|-------------:|:---------------------------:|:--------------------:|
| uid           |          INT |       AUTO_INCREMENT        |       username       |
| username      |  VARCHAR(20) |       NOT NULL UNIQUE       |    user_password     |
| salt          |  VARCHAR(32) |                             |    user_password     |
| phone         |  VARCHAR(20) |                             |         電話番号         |
| email         |  VARCHAR(30) |                             |        email         |
| gender        |          INT |                             |       0:女、1:男        |
| avatar        |  VARCHAR(50) |                             |       ユーザーアイコン       |
| is_delete     |          INT |                             |     0:削除済、1:未削除      |
| created_user  |  VARCHAR(20) |                             |        表の作成者         |
| created_time  |     DATETIME |                             |        表の作成時間        |
| modified_user |  VARCHAR(20) |                             |       最後に修正した者       |
| modified_time |     DATETIME |                             |      最後に修正した時間       |

### t_address

| column        |        type |           option           |  option   |
|:--------------|------------:|:--------------------------:|:---------:|
| addressId     |         INT | AUTO_INCREMENT,PRIMARY KEY | addressId |
| userId        |         INT |                            |  userId   |
| name          | VARCHAR(20) |                            | 受け取り人の名前  |
| province_name | VARCHAR(15) |                            |  都道府県名前   |
| province_code |     CHAR(6) |                            |  都道府県コード  |
| city_name     | VARCHAR(15) |                            |    市名前    |
| city_code     |     CHAR(6) |                            |   市コード    |
| area_name     | VARCHAR(15) |                            |    区名前    |
| area_code     |     CHAR(6) |                            |   区コード    |
| zip           |     CHAR(6) |                            |   郵便番号    |
| address       | VARCHAR(50) |                            |   詳細住所    |
| phone         | VARCHAR(20) |                            |   携帯番号    |
| tel           | VARCHAR(20) |                            |  固定電話番号   |
| tag           |  VARCHAR(6) |                            |   住所のタグ   |
| is_default    |         INT |                            |   デフォルト   |
| created_user  | VARCHAR(20) |                            |   表の作成者   |
| created_time  |    DATETIME |                            |  表の作成時間   |
| modified_user | VARCHAR(20) |                            | 最後に修正した者  |
| modified_time |    DATETIME |                            | 最後に修正した時間 |

