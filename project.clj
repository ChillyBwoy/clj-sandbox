(defproject sandbox "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2197"]]

  :source-paths ["src/clj" "src/cljs"]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {:builds
              [{:source-paths ["src/cljs/sandbox/apps"]
                :compiler
                {:output-dir "resources/public/javascripts"
                 :output-to "resources/public/javascripts/app.js"
                 :pretty-print false
                 :source-map "resources/public/javascripts/app.js.map"}}]})
