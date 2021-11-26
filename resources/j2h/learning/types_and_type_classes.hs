removeNonUppercase :: [Char] -> [Char]  
removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z']]   
types1 = removeNonUppercase "aSv32XdGnsEFA"

addThree :: Int -> Int -> Int -> Int  
addThree x y z = x + y + z  

factorial :: Integer -> Integer  
factorial n = product [1..n] 

sumInt :: Int -> Integer 
sumInt amount = sum (take amount [1..])

sumInt2 :: Integer -> Integer 
sumInt2 amount = sum [1.. amount]

tuple1 = maxBound :: (Int, Int)
tuple2 = minBound :: (Int, Int)