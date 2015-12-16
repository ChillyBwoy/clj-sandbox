(defproject sandbox "0.0.1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]]

  :source-paths ["src/clj" "src/cljs"]
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]]
  :cljsbuild {
              :builds [{:id "particles"
                        :source-paths ["src/cljs/sandbox/apps"]
                        :figwheel true
                        :compiler {:main "sandbox.apps.particles"
                                   :asset-path "javascripts/particles/out"
                                   :output-to "resources/public/javascripts/particles/app.js"
                                   :output-dir "resources/public/javascripts/particles/out"
                                   }}]})
