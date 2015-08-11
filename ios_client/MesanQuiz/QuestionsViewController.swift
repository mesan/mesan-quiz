import Foundation
import UIKit

class QuestionsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    let cellIdentifier = "QuestionCell"
    var questions: [Question]!
    
    override func viewDidLoad() {
        questions = []
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return questions.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let question = questions[indexPath.row]
        
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! QuestionCell
        
        return cell
    }
}
