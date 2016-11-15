(ns frontend.main
  (:require [frontend.events :as events]
            [frontend.subs :as subs]
            [frontend.views :as views]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]))

(defn ^:export main
  []
  (re-frame/dispatch-sync [:db/initialize])
  (re-frame/dispatch-sync [:sente/connect])
  (reagent/render [views/main-view]
                  (.getElementById js/document "app")))

(main)
