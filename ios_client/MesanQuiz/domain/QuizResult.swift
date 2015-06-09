import Foundation

class QuizResult {
    var fullName: String
    var score: Int
    var numberOfQuestions: Int
    
    init(fullName: String, score: Int, numberOfQuestions: Int) {
        self.fullName = fullName
        self.score = score
        self.numberOfQuestions = numberOfQuestions
    }
}
