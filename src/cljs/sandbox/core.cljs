(ns sandbox.core
 (:require [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:9000/repl")

;(ns sandbox.core
;  (:require [sandbox.lib.canvas :as canvas]))
;
;(enable-console-print!)
;
;(def context (canvas/init "display"))
;
;(defn ^:export run []
;  (print context))
