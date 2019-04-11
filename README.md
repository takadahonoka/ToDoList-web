# ToDoList-web

【 使用した技術要素 】

・Java => 処理。<br>
・Bootstrap => デザイン。<br>
・Javascript => 処理、デザイン。<br>
・MySQL => データベース。<br>

【 全体の設計・構成についての説明 】

MySQL(DB構成)、システムの設計・構成 :https://drive.google.com/open?id=1jdmJI1IUgm19YJDuJArhHT8D6kPSlNLy<br>

【 開発環境のセットアップ手順 】

※ 大変申し訳ございません。<br>
前準備にEclipseとxamppを起動していて使える状態である事を前提として
書いています。

今回はEclipseとxamppを利用しました。

1. プロジェクトのクローンとTomcatのダウンロード。<br>
このURLのプロジェクトをクローンしてください。
https://github.com/takadahonoka/ToDoList-web.git<br>
このURLのTomcatをダウンロードしてください。<br>
<br>

2. workspaceの作成。<br>
まず、workspace用のフォルダを作成します。<br>
そして、¥ファイル¥ワークスペースの切り替え¥その他 を開き、先ほど作成したworkspace用のフォルダを指定します。<br>
次に、¥ウィンドウ¥パースペクティブ¥パースペクティブを開く¥その他 を開き、「Java EE」を選択します。<br>
次にサーバーを作成します。<br>
¥ファイル¥新規¥その他 を開き、¥サーバー¥サーバーを選択し、次へをクリックします。<br>
「Tomcat v8.0 サーバー」を選択し、追加をクリックし、1でダウンロードしたTomcatのサーバーを選択し、完了をクリックします。<br>

3. ファイルからworkspaceでプロジェクトを開く。<br>
¥ファイル¥ファイル・システムからプロジェクトを開く を選択し、ディレクトリをクリックし、先ほどクローンしたプロジェクトのフォルダを選択します。<br>
そして、完了をクリックします。<br>
この時にビルドパスでエラーが出た場合は、ToDoListプロジェクトを選択し、右クリックします。
¥ビルド・パス¥ビルド・パス¥ライブラリをクリックします。外部jarの追加をクリックします。
1でダウンロードした¥apache-tomcat-8.0.48¥lib¥servlet-api.jar を選択し、適用します。

4. SQL文の実行。<br>
このURL先の「ToDo-SQL」ファイルを、そのままコピペし、実行します。<br>
https://drive.google.com/open?id=1jdmJI1IUgm19YJDuJArhHT8D6kPSlNLy<br>

5. プロジェクトの実行。<br>
まずは、¥Webcontent¥index.jspを開きます。<br>
そして、右向きの緑と白の三角ボタンの、右にある下向きの黒い三角ボタンをクリックし、¥実行¥1 サーバーで実行 をクリックします。<br>
次に、2で作成したサーバーを選択し完了をクリックします。<br>
最後に、実行されたページのURLをChromeのブラウザで検索してもらうと、ページが表示されると思います。<br>

6. 終了。
