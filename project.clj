(defproject tiko-example "0.1.0-SNAPSHOT"
  :description "Simple full-stack Clojure example project"
  :url "https://github.com/MattiNieminen/tiko-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]]
  :main ^:skip-aot backend.main
  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj" "test/cljs" "test/cljc"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
