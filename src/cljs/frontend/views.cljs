(ns frontend.views
  (:require [common.localization :refer [tr]]
            [re-frame.core :as re-frame]))

(defn todo-item
  [todo]
  [:div.todo-item todo])

(defn todo-list
  []
  (let [todos (re-frame/subscribe [:todos])]
    [:div.todo-list
     (for [[idx todo] (map-indexed vector @todos)]
       ^{:key idx}
       [todo-item todo])]))

(defn new-todo-input
  []
  (let [new-todo (re-frame/subscribe [:new-todo])]
    [:div.new-todo
     [:input.new-todo__input
      {:placeholder (tr :new-todo)
       :value @new-todo
       :on-change (fn [e]
                    (let [value (-> e .-target .-value)]
                      (re-frame/dispatch [:update-new-todo
                                          {:new-todo value}])))
       :on-key-press (fn [e]
                       (if (= 13 (.-charCode e))
                         (re-frame/dispatch [:create-todo])))}]]))

(defn main-view
  []
  [:div.todo-container
   [:h1.todo-title (tr :tiko-todo)]
   [new-todo-input]
   [todo-list]])
