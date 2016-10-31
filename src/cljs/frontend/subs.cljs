(ns frontend.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :todos
 (fn [db _]
   (:todos db)))

(re-frame/reg-sub
 :new-todo
 (fn [db _]
   (:new-todo db)))
