# Todo-App-Spring-Boot
*「Todoリスト」アプリケーション*

## 使用した技術要素
* Spring Boot
* Thymeleaf
* MySQL
* Bootstrap 4

## 設計・構成説明
全体的に、仕様書に基づいた設計を心がけました：
* トップ画面：新たなTodoタスクの追加、現登録済みTodoの数及び内容の確認等はこの画面で行えます。リスト上のTodoは内容の編集と削除が出来、完了/未完了ステータスをクリックすることで反転出来ます。Bootstrap4のカードコンポーネントを主に使用しました。
* 編集画面：Todoの内容を更新出来ます。更新が成功すると、トップ画面に戻ります。
* 検索画面：登録済みのTodoの検索が行なえます。Todoの変更仕様は、トップ画面と同じです。
* 共通ヘッダー：どの画面からでも、トップ画面と検索画面へ行くことが出来ます。

## 開発環境セットアップ手順
1. [Spring Initializr](https://start.spring.io/)で：Gradle Project; Java; Spring Boot 2.16; Packaging Jar; Java 8を選び、Dependencies: Web; JPA; Thymeleaf; DevTools; MySQLを選ぶ
2. MySQL Workbenchにてデータベース作成、src/main/resources/application.propertiesに設定内容を記載、使えるようにする
3. bootstrap4, JQueryをsrc/main/resources/staticに配置

## その他
Spring Bootの不慣れ、時間の都合等により、追加機能等を盛り込めなかった事、UI調整が不十分な事が残念だったと感じます。チームラボのインターンシップが始まる前に、もっとしっかりSpring Boot関連の知識を補完するよう心がけます。