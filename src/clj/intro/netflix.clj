(ns intro.netflix)

(def series [{:name "Stranger Things"
              :episode-count 10
              :rating 5
              :genre :horror}
             {:name "Daredevil"
              :episode-count 20
              :rating 4
              :genre :action}
             {:name "Jessica Jones"
              :episode-count 10
              :rating 5
              :genre :action}
             {:name "Hemlock Grove"
              :episode-count 36
              :rating 1
              :genre :horror}
             {:name "Marco Polo"
              :episode-count 22
              :rating 4
              :genre :drama}
             {:name "Luke Cage"
              :episode-count 12
              :rating 3
              :genre :action}
             {:name "House of Cards"
              :episode-count 32
              :rating 5
              :genre :drama}])

;;
;; Higher-order functions are functions, that either take functions
;; as arguments, or return them.
;;

;;
;; Map (function, not the data structure)
;;

;; Getting a list of all series names


;; Simpler: (keywords can work as functions)


;;
;; Filter
;;

;; Getting a list of series, where :genre is horror


;;
;; Reduce
;;

;; Get the sum of episodes for all series


;;
;; Exercise 3. Get the amount of shows which have :horror as :genre
;;


;;
;; Exercise 4. Combine map, filter and reduce to get the total amount of
;; episodes in all series where :genre is :action. Write a helper function
;; action? which takes a single series as parameter and use that with filter.
;;

