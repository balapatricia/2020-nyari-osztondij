#lang racket

;Egyszemélyes játékok
;Visszalépéses keresés

(define (backtrack-search initialState operators apply-operator? apply-operator goal?)
  (let helper ((lst (list (list initialState null (filter (curry apply-operator? initialState) operators)))))
    #;(printf "~a~n" (caar lst))
    #;(printf "~a~n" (caddar lst))
    #;(printf "~a~n" (cdr (caddar lst)))
    (cond
        [(empty? lst) lst]
        [(goal? (caar lst)) lst]
        [(empty? (caddar lst)) (helper (cdr lst))]
        [(member (apply-operator (caar lst) (car(caddar lst))) lst (λ (a b) (equal? a (car b)))) (helper (cons (list (caar lst) (cadar lst) (cdr (caddar lst))) (cdr lst)))]
        [else (helper (cons
                       (list (apply-operator (caar lst) (car(caddar lst))) (car(caddar lst)) (filter (curry apply-operator? (apply-operator (caar lst) (car(caddar lst)))) operators))
                       (cons (list (caar lst) (cadar lst) (cdr (caddar lst))) (cdr lst))))])))


#;(backtrack-search
 '(A A A)
 '((A B 1) (A B 2) (A B 3) (A C 1) (A C 2) (A C 3) (B A 1) (B A 2) (B A 3)
   (B C 1) (B C 2) (B C 3) (C A 1) (C A 2) (C A 3) (C B 1) (C B 2) (C B 3))
 (λ (s op)
   (define (from s n) 
     (cond
       [(empty? s) #f]
       [(equal? (car op) (car s)) n]
       [else (from (cdr s) (add1 n))]))
   (define (to s n)
     (cond
       [(empty? s) #f]
       [(equal? (cadr op) (car s)) n]
       [else (to (cdr s) (add1 n))]))
   (and (not(not (from s 0))) (= (from s 1) (caddr op)) (or (not(to s 0)) (> (to s 1) (caddr op)))))
 (λ (s op)
   (let helper ((s s) (res '()) (op op) (change? #f))
     (cond
       [(empty? s) (reverse res)]
       [change? (helper (cdr s) (cons (car s) res) op change?)]
       [(equal? (car s) (car op)) (helper (cdr s) (cons (cadr op) res) op #t)]
       [else (helper (cdr s) (cons (car s) res) op change?)])))
 (λ (s)
   (equal? s '(C C C))))


;Mélységi keresés

(define (depth-first-search initialState operators apply-operator? apply-operator goal?)
  (let helper ((open (list (list initialState null 0 null))) (closed '()))
    (cond
      [(empty? open) open]
      [(goal? (caar open))
       (let loop ((item (car open)) (result (list (list (second (car open)) (caar open) (third (car open))))))
         (printf "~a ~a ~a~n" (second item) (car item) (third item))
         (cond
           [(empty? (fourth item)) result]
           [else (loop (fourth item) (cons (list (second (fourth item)) (car(fourth item)) (third(fourth item))) result))]))]
      [else
         (let loop ((s (caar open)) (op operators) (depth (third (car open)))  (open (cdr open)) (closed (cons (car open) closed)))
           (cond
            [(empty? op) (helper open closed)]
            [(and (apply-operator? s (car op)) (not (member (apply-operator s (car op)) open (λ (a b) (equal? a (car b))))) (not (member (apply-operator s (car op)) closed (λ (a b) (equal? a (car b))))))
             (loop s (cdr op) depth (cons (list (apply-operator s (car op)) (car op) (add1 depth) (car closed)) open) closed)]
            [else (loop s (cdr op) depth open closed)]))])))
         

#;(depth-first-search
 '(A A A)
 '((A B 1) (A B 2) (A B 3) (A C 1) (A C 2) (A C 3) (B A 1) (B A 2) (B A 3) (B C 1) (B C 2) (B C 3) (C A 1) (C A 2) (C A 3) (C B 1) (C B 2) (C B 3))
 (λ (s op)
   (define (from s n) 
     (cond
       [(empty? s) #f]
       [(equal? (car op) (car s)) n]
       [else (from (cdr s) (add1 n))]))
   (define (to s n)
     (cond
       [(empty? s) #f]
       [(equal? (cadr op) (car s)) n]
       [else (to (cdr s) (add1 n))]))
   (and (not(not (from s 0))) (= (from s 1) (caddr op)) (or (not(to s 0)) (> (to s 1) (caddr op)))))
 (λ (s op)
   (let helper ((s s) (res '()) (op op) (change? #f))
     (cond
       [(empty? s) (reverse res)]
       [change? (helper (cdr s) (cons (car s) res) op change?)]
       [(equal? (car s) (car op)) (helper (cdr s) (cons (cadr op) res) op #t)]
       [else (helper (cdr s) (cons (car s) res) op change?)])))
 (λ (s)
   (equal? s '(C C C))))
         

;Szélességi keresés

(define (breadth-first-search initialState operators apply-operator? apply-operator goal?)
  (let helper ((open (list (list initialState null 0 null))) (closed '()))
    (cond
      [(empty? open) open]
      [(goal? (caar open))
       (let loop ((item (car open)) (result (list (list (second (car open)) (caar open) (third (car open))))))
         (printf "~a ~a ~a~n" (second item) (car item) (third item))
         (cond
           [(empty? (fourth item)) result]
           [else (loop (fourth item) (cons (list (second (fourth item)) (car(fourth item)) (third(fourth item))) result))]))]
      [else
         (let loop ((s (caar open)) (op operators) (depth (third (car open)))  (open (cdr open)) (closed (cons (car open) closed)))
           (cond
            [(empty? op) (helper open closed)]
            [(and (apply-operator? s (car op)) (not (member (apply-operator s (car op)) open (λ (a b) (equal? a (car b))))) (not (member (apply-operator s (car op)) closed (λ (a b) (equal? a (car b))))))
             (loop s (cdr op) depth (append open (list(list (apply-operator s (car op)) (car op) (add1 depth) (car closed)))) closed)]
            [else (loop s (cdr op) depth open closed)]))])))


#;(breadth-first-search
 '(A A A)
 '((A B 1) (A B 2) (A B 3) (A C 1) (A C 2) (A C 3) (B A 1) (B A 2) (B A 3) (B C 1) (B C 2) (B C 3) (C A 1) (C A 2) (C A 3) (C B 1) (C B 2) (C B 3))
 (λ (s op)
   (define (from s n) 
     (cond
       [(empty? s) #f]
       [(equal? (car op) (car s)) n]
       [else (from (cdr s) (add1 n))]))
   (define (to s n)
     (cond
       [(empty? s) #f]
       [(equal? (cadr op) (car s)) n]
       [else (to (cdr s) (add1 n))]))
   (and (not(not (from s 0))) (= (from s 1) (caddr op)) (or (not(to s 0)) (> (to s 1) (caddr op)))))
 (λ (s op)
   (let helper ((s s) (res '()) (op op) (change? #f))
     (cond
       [(empty? s) (reverse res)]
       [change? (helper (cdr s) (cons (car s) res) op change?)]
       [(equal? (car s) (car op)) (helper (cdr s) (cons (cadr op) res) op #t)]
       [else (helper (cdr s) (cons (car s) res) op change?)])))
 (λ (s)
   (equal? s '(C C C))))


;----------------------------------------------------------------------------------------------------------------------

;Kétszemélyes játékok
;Minimax algoritmus

(define (minimax game level)
  (let ((initial-state (first game)) (operators (second game)) (apply-operator? (third game))
        (apply-operator (fourth game)) (end-state? (fifth game)) (utility-function (sixth game)))
    (cond
    [(or (= level 0) (end-state? initial-state)) (list (utility-function initial-state) '())]
    [(equal? (second initial-state) 'A)
     (let helper((res (list -inf.0 '())) (op operators))
       (cond
         [(empty? op) res]
         [(apply-operator? initial-state (car op))
          (let ((mm (minimax (cons (apply-operator initial-state (car op)) (cdr game)) (sub1 level))))
            (cond
              [(> (car mm) (car res)) (helper (list (car mm) (car op)) (cdr op))]
              [else (helper res (cdr op))]))]
         [else (helper res (cdr op))]))]
    [else
     (let helper((res (list +inf.0 '())) (op operators))
       (cond
         [(empty? op) res]
         [(apply-operator? initial-state (car op))
          (let ((mm (minimax (cons (apply-operator initial-state (car op)) (cdr game)) (sub1 level))))
            (cond
              [(< (car mm) (car res)) (helper (list (car mm) (car op)) (cdr op))]
              [else (helper res (cdr op))]))]
         [else (helper res (cdr op))]))])))


;Alfabéta algoritmus

(define (alphabeta game level alpha beta)
  (let ((initial-state (first game)) (operators (second game)) (apply-operator? (third game)) (apply-operator (fourth game)) (end-state? (fifth game)) (utility-function (sixth game)))
    (printf "~a~n" initial-state)
    (cond
       [(or (= level 0) (end-state? initial-state)) (list (utility-function initial-state) '())]
       [(equal? (second initial-state) 'A)
        (let helper((res (list alpha '())) (op operators))
          (cond
            [(empty? op)
             (cond
               [(< (car res) beta) res]
               [else (list beta (cdr res))])]
            [(>= (car res) beta) (cons beta (cdr res))]
            [(apply-operator? initial-state (car op))
             (let ((ab (alphabeta (cons (apply-operator initial-state (car op)) (cdr game)) (sub1 level) (car res) beta)))
               (cond
                 [(< (car res) (car ab)) (helper (list (car ab) (car op)) (cdr op))]
                 [else (helper res (cdr op))]))]
            [else (helper res (cdr op))]))]
       [else
        (let helper((res (list beta '())) (op operators))
          (cond
            [(empty? op)
             (cond
               [(> (car res) alpha) res]
               [else (list alpha (cdr res))])]
            [(<= (car res) alpha) (cons alpha (cdr res))]
            [(apply-operator? initial-state (car op))
             (let ((ab (alphabeta (cons (apply-operator initial-state (car op)) (cdr game)) (sub1 level) alpha (car res))))
               (cond
                 [(> (car res) (car ab)) (helper (list (car ab) (car op)) (cdr op))]
                 [else (helper res (cdr op))]))]
            [else (helper res (cdr op))]))])))
 
       
;Coin Game állapottér reprezentációja

(define (coin-game)
  (define (end-state? s)
    (let loop ((item (car s)))
         (cond
           [(empty? item) #t]
           [(> (car item) 2) #f]
           [else (loop (cdr item))])))
  (list '((7) A)
    '((1 2) (4 2) (5 2) 
         (1 1) (2 3) (3 2) 
         (6 1) (3 3) (5 1) 
         (4 1) (2 1) (5 3) 
         (6 3) (3 1) (4 3) 
         (6 2) (1 3) (2 2) )
     (λ (s op)
       (cond
         [(or (> (car op) (length (car s))) (< (car op) 1)) #f]
         [(<= (list-ref (car s) (sub1 (car op))) 2) #f]
         [(or (< (second op) 1) (> (second op) ( / (list-ref (car s) (sub1 (car op))) 2))) #f]
         [else #t]))
     (λ (s op)
       (cond
         [(equal? (second s) 'A) (list (append (take (car s) (sub1 (car op))) (list (second op)) (list-tail (car s) (car op)) (list (- (list-ref (car s) (sub1 (car op))) (second op)))) 'B)]
         [else (list (append (take (car s) (sub1 (car op))) (list (second op)) (list-tail (car s) (car op)) (list(- (list-ref (car s) (sub1 (car op))) (second op)))) 'A)]))
     end-state?  
     (λ (s)
       (cond
         [(and (end-state? s) (equal? (second s) 'B)) 10]
         [(and (end-state? s) (equal? (second s) 'A)) -10]
         [else
          (let ((equals_3 (length (filter (curry = 3) (car s)))) (equals_4 (length (filter (curry = 4) (car s)))) (greater_4 (length (filter (curry < 4) (car s)))))
            (cond
              [(and (= equals_3 0) (= equals_4 1) (= greater_4 0) (equal? (second s) 'A)) 8]
              [(and (= equals_3 0) (= equals_4 1) (= greater_4 0) (equal? (second s) 'B)) -8]
              [(and (= (modulo equals_3 2) 0) (= equals_4 0) (= greater_4 0) (equal? (second s) 'A)) -9]
              [(and (= (modulo equals_3 2) 0) (= equals_4 0) (= greater_4 0) (equal? (second s) 'B)) 9]
              [(and (= equals_3 0) (= (modulo equals_4 2) 0) (= greater_4 0) (equal? (second s) 'A)) -7]
              [(and (= equals_3 0) (= (modulo equals_4 2) 0) (= greater_4 0) (equal? (second s) 'B)) 7]
              [(and (= (modulo (+ equals_3 equals_4) 2) 0) (= greater_4 0) (equal? (second s) 'A)) -5]
              [(and (= (modulo (+ equals_3 equals_4) 2) 0) (= greater_4 0) (equal? (second s) 'B)) 5]
              [else 0]))]))))
  


;(minimax (coin-game) 5)

;(alphabeta (coin-game) 5 -inf.0 +inf.0)


(define (play game is-playing-against-human? is-machine-start? is-working-with-minimax? is-working-with-alphabeta?
              is-there-step-proposal? depth-for-machine depth-for-human)
  (let ((initial-state (first game)) (operators (second game)) (apply-operator? (third game)) (apply-operator (fourth game)) (end-state? (fifth game)) (utility-function (sixth game)))
  (let loop ((state initial-state))
    (printf "Current state: ~a~n" state)
    (cond
      [(end-state? state)
       (cond
         [(equal? (second state) 'B) "The winner is player A!"]
         [(equal? (second state) 'A) "The winner is player B!"]
         [else "The game is tied!"])]
      [(and (not is-playing-against-human?) (or (and is-machine-start? (equal? (second state) 'A)) (and (not is-machine-start?) (equal? (second state) 'B))))
       (cond
         [(and is-working-with-minimax? (not is-working-with-alphabeta?))
          (let ((step (second (minimax (cons state (cdr game)) depth-for-machine))))
            (printf "Machine's move: ~a~n" step)
            (loop (apply-operator state step)))]
         [(and is-working-with-alphabeta? (not is-working-with-minimax?))
          (let ((step (second (alphabeta (cons state (cdr game)) depth-for-machine -inf.0 +inf.0))))
            (printf "Machine's move: ~a~n" step)
            (loop (apply-operator state step)))])]
      [else
       (cond
         [is-there-step-proposal?
          (cond
            [(and is-working-with-minimax? (not is-working-with-alphabeta?))
              (printf "The suggestion is: ~a~n" (second (minimax (cons state (cdr game)) depth-for-machine)))]
            [(and is-working-with-alphabeta? (not is-working-with-minimax?))
             (printf "The suggestion is: ~a~n" (second(alphabeta (cons state (cdr game)) depth-for-machine -inf.0 +inf.0)))])])  
       (let helper ((step (map string->number (string-split (read-line)))))
         (cond
           [(apply-operator? state step) (loop (apply-operator state step))]
           [else
            (printf "~a~n" "Invalid move.")
            (helper (map string->number (string-split (read-line))))]))]))))
       
       

(play (coin-game) #f #f #t #f #t 5 5)
;(play (coin-game) #f #f #f #t #t 5 5)     
