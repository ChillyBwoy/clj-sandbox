(defproject sandbox "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2197"]]

  :source-paths ["src/clj" "src/cljs"]
  :plugins [[lein-cljsbuild "1.0.3"]
            [com.cemerick/clojurescript.test "0.3.1"]]
  :aliases {"auto-test" ["do" "clean," "cljsbuild" "auto" "test"]}
  :cljsbuild {:builds
              {:apps
               {:source-paths ["src/cljs/sandbox/apps"]
                :compiler
                {:output-dir "resources/public/javascripts/target"
                 :output-to "resources/public/javascripts/app.js"
                 :pretty-print false
                 :source-map "resources/public/javascripts/app.js.map"}}
               :test
               {:source-paths ["test/cljs"]
                :notify-command ["phantomjs" :cljs.test/runner "test/javascripts/tests.js"]
                :compiler
                {:output-to "test/javascripts/tests.js"
                 :optimizations :whitespace
                 :pretty-print true}}}
              ;;:test-commands {"unit-tests" ["phantomjs" :runner "test/javascripts/tests.js"]}
              })
