(ns lambda-calculus.core-test
  (:require [clojure.test :refer :all]
            [lambda-calculus.core :refer :all]))

(deftest logic-test
  (is (= (TRUE "first" "second") "first"))
  (is (= (FALSE "first" "second") "second"))

  (is (= (AND TRUE TRUE) TRUE))
  (is (= (AND TRUE FALSE) FALSE))
  (is (= (AND FALSE TRUE) FALSE))
  (is (= (AND FALSE FALSE) FALSE))

  (is (= (OR TRUE TRUE) TRUE))
  (is (= (OR TRUE FALSE) TRUE))
  (is (= (OR FALSE TRUE) TRUE))
  (is (= (OR FALSE FALSE) FALSE))

  (is (= (NOT TRUE) FALSE))
  (is (= (NOT FALSE) TRUE))

  (is (= (XOR TRUE TRUE) FALSE))
  (is (= (XOR TRUE FALSE) TRUE))
  (is (= (XOR FALSE TRUE) TRUE))
  (is (= (XOR FALSE FALSE) FALSE)))

(deftest numeric-tests
  (is (= (to-int (SUCC ZERO)) (to-int ONE)))
  (is (= (to-int (SUCC ONE)) (to-int TWO)))
  (is (= (to-int (SUCC TWO)) (to-int THREE)))
  (is (= (to-int (SUCC THREE)) (to-int FOUR)))

  (is (= (to-int (PREDv1 ONE)) (to-int ZERO)))
  (is (= (to-int (PREDv1 TWO)) (to-int ONE)))
  (is (= (to-int (PREDv1 THREE)) (to-int TWO)))
  (is (= (to-int (PREDv1 FOUR)) (to-int THREE)))

  (is (= (to-int (PRED ONE)) (to-int ZERO)))
  (is (= (to-int (PRED TWO)) (to-int ONE)))
  (is (= (to-int (PRED THREE)) (to-int TWO)))
  (is (= (to-int (PRED FOUR)) (to-int THREE))))

(deftest operator-tests
  (is (= (to-int (PLUS ZERO ONE)) (to-int ONE)))
  (is (= (to-int (PLUS ONE TWO)) (to-int THREE)))
  (is (= (to-int (PLUS TWO TWO)) (to-int FOUR)))

  (is (= (to-int (MINUS ONE ZERO)) (to-int ONE)))
  (is (= (to-int (MINUS TWO ONE)) (to-int ONE)))
  (is (= (to-int (MINUS TWO TWO)) (to-int ZERO)))
  (is (= (to-int (MINUS THREE ONE)) (to-int TWO)))

  (is (= (to-int (MULT ZERO TWO)) (to-int ZERO)))
  (is (= (to-int (MULT TWO ONE)) (to-int TWO)))
  (is (= (to-int (MULT TWO TWO)) (to-int FOUR)))
  (is (= (to-int (MULT THREE TWO)) (to-int SIX)))

  (is (= (to-int (EXP ZERO TWO)) (to-int ZERO)))
  (is (= (to-int (EXP ONE TWO)) (to-int ONE)))
  (is (= (to-int (EXP TWO ZERO)) (to-int ONE)))
  (is (= (to-int (EXP TWO ONE)) (to-int TWO)))
  (is (= (to-int (EXP TWO TWO)) (to-int FOUR)))
  (is (= (to-int (EXP TWO THREE)) (to-int EIGHT)))
  (is (= (to-int (EXP THREE TWO)) (to-int NINE)))
  (is (= (to-int (EXP THREE THREE)) (to-int (MULT NINE THREE)))))

(deftest comparator-tests
  (is (= (to-bool (IS_ZERO ZERO)) true))
  (is (= (to-bool (IS_ZERO ONE)) false))
  (is (= (to-bool (IS_ZERO TEN)) false))

  (is (= (to-bool (LESS_THAN_OR_EQUAL ONE ZERO)) false))
  (is (= (to-bool (LESS_THAN_OR_EQUAL ONE ONE)) true))
  (is (= (to-bool (LESS_THAN_OR_EQUAL ONE TWO)) true))

  (is (= (to-bool (LESS_THAN ONE ZERO)) false))
  (is (= (to-bool (LESS_THAN ONE ONE)) false))
  (is (= (to-bool (LESS_THAN ONE TWO)) true))

  (is (= (to-bool (GREATER_THAN_OR_EQUAL ONE ZERO)) true))
  (is (= (to-bool (GREATER_THAN_OR_EQUAL ONE ONE)) true))
  (is (= (to-bool (GREATER_THAN_OR_EQUAL ONE TWO)) false))

  (is (= (to-bool (GREATER_THAN ONE ZERO)) true))
  (is (= (to-bool (GREATER_THAN ONE ONE)) false))
  (is (= (to-bool (GREATER_THAN ONE TWO)) false))

  (is (= (to-bool (NOT_ZERO ZERO)) false))
  (is (= (to-bool (NOT_ZERO ONE)) true))
  (is (= (to-bool (NOT_ZERO TEN)) true)))
