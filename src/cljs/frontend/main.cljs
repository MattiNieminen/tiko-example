(ns frontend.main
  (:require [frontend.events :as events]
            [frontend.subs :as subs]
            [frontend.views :as views]
            [reagent.core :as reagent]))

(defn ^:export main
  []
  (reagent/render [views/hello]
                  (.getElementById js/document "app")))

(main)
