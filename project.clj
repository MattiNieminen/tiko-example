(defproject tiko-example "0.1.0-SNAPSHOT"
  :description "Simple full-stack Clojure example project"
  :url "https://github.com/MattiNieminen/tiko-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]

                 ;; TODO: change this to org.clojure/tools-namespace when bug
                 ;; http://dev.clojure.org/jira/browse/TNS-45
                 ;; is fixed
                 [metosin.forks/tools.namespace "0.3.0-20160926.120815-2"]
                 [metosin/lokit "0.1.0"]
                 [mount "0.1.10"]
                 [org.immutant/web "2.1.5"]
                 [com.taoensso/sente "1.11.0"]
                 [ring/ring-defaults "0.3.0-beta1"]
                 [hiccup "1.0.5"]
                 [re-frame "0.8.0"]
                 [reagent "0.6.0"]]
  :main ^:skip-aot backend.main
  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj" "test/cljs" "test/cljc"]
  :target-path "target/%s"
  :repl-options {:init-ns user}
  :profiles {:dev {:resource-paths ["target/dev/resources"]
                   :sass {:target-path "target/dev/resources/css"}}
             :production {:resource-paths ["target/production/resources"]
                          :sass {:target-path "target/production/resources/css"}
                          :aot [backend.main]}}
  :plugins [[lein-pdo "0.1.1"]
            [deraen/lein-sass4clj "0.3.0"]
            [lein-cljsbuild "1.1.4"]
            [lein-figwheel "0.5.8"]]
  :sass {:source-paths ["src/sass"]
         :source-map true
         :output-style :compressed}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljc" "src/cljs"]
                        :figwheel true
                        :compiler
                        {:main "frontend.main"
                         :asset-path "js/out"
                         :output-to "target/dev/resources/js/main.js"
                         :output-dir "target/dev/resources/js/out"}}
                       {:id "production"
                        :source-paths ["src/cljc" "src/cljs"]
                        :compiler
                        {:main "frontend.main"
                         :optimizations :advanced
                         :output-to "target/production/resources/js/main.js"
                         :output-dir "target/production/resources/js/out"}}]}
  :figwheel {:css-dirs ["target/dev/resources/css"]}
  :auto-clean false
  :aliases {"develop" ["do" "clean"
                       ["pdo" ["sass4clj" "auto"] ["figwheel"]]]
            "production" ["with-profile" "production" "do"
                          "clean"
                          ["sass4clj" "once"]
                          ["cljsbuild" "once" "production"]
                          "uberjar"]})
