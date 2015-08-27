import Foundation
import UIKit

class QuestionsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    let cellIdentifier = "QuestionCell"
    var quiz: Game!
    let gameService = GameService()
    
    override func viewDidLoad() {
        
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.quiz.questions!.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let question = self.quiz.questions![indexPath.row]
        
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! QuestionCell
        cell.setQuestion(question)
        
        return cell
    }
    
    @IBAction func reset(segue: UIStoryboardSegue) {
        let createQuestionViewController = segue.sourceViewController as! CreateQuestionViewController
        let question = createQuestionViewController.question
        self.quiz.questions?.append(question)
        self.tableView.reloadData()
    }
    
    @IBAction func createQuiz(sender: AnyObject) {
        gameService.createGame(self.quiz)
        
        self.navigationController!.popToRootViewControllerAnimated(true)
    }
}
