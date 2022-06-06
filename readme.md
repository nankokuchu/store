### t_user

| column        |        type |     option      |    option     |
|:--------------|------------:|:---------------:|:-------------:|
| uid           |         INT | AUTO_INCREMENT  |   username    |
| username      | VARCHAR(20) | NOT NULL UNIQUE | user_password |
| salt          | VARCHAR(32) |                 | user_password |
| phone         | VARCHAR(20) |                 |     電話番号      |
| email         | VARCHAR(30) |                 |     email     |
| gender        |         INT |                 |    0:女、1:男    |
| avatar        | VARCHAR(50) |                 |   ユーザーアイコン    |
| is_delete     |         INT |                 |   0:削除済、1:未削除   |
| created_user  | VARCHAR(20) |                 |     表の作成者     |
| created_time  | DATETIME |                 |    表の作成時間     |
| modified_user | VARCHAR(20) |                 |   最後に修正した者    |
| modified_time |    DATETIME |                 |   最後に修正した時間   |
