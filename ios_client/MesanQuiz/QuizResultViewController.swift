import Foundation
import UIKit

class QuizResultViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var feedbackLabel: UILabel!
    
    var cellIdentifier = "QuizResultCell"

    var quizResults: [QuizResult] = []
    
    var newResult: QuizResult!
    
    func addNewResult(quizResult: QuizResult) {
        newResult = quizResult
        quizResults.append(newResult)
    }
    
    override func viewDidLoad() {
        scoreLabel.text = "\(newResult.score) / \(newResult.numberOfQuestions) riktige!"
        
        var percentage = Double(newResult.score)/Double(newResult.numberOfQuestions)
        switch percentage {
        case 1:
            feedbackLabel.text = "Awesome! 😎"
        case 0.5..<1:
            feedbackLabel.text = "Ganske bra! 😊"
        default:
            feedbackLabel.text = "Ikke bra nok! 😐"
        }
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return quizResults.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! QuizResultCell
        
        var quizResult = quizResults[indexPath.row]
        cell.setQuizResult(quizResult)
        
        return cell
    }

}
