(ns frontend.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :todo/all
 (fn [db _]
   (:todos db)))

(re-frame/reg-sub
 :todo/new
 (fn [db _]
   (:new-todo db)))
