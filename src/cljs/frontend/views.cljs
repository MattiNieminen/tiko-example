(ns frontend.views
  (:require [re-frame.core :as re-frame]))

(defn hello
  []
  (let [hello (re-frame/subscribe [:hello])
        shout (re-frame/subscribe [:shout])]
    (fn []
      [:div
       [:button
        {:on-click #(re-frame/dispatch [:say-hello "Hello"])}
        "Say hello to server!"]
       (if @hello
         [:p (str "Server says " @hello)])
       (if @shout
         [:p (str "Server shouts " @shout)])])))
