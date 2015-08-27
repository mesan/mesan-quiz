import Foundation
import UIKit

class CreateQuizViewController: UIViewController {
    
    @IBOutlet weak var quiznameText: UITextField!
    @IBOutlet weak var topicText: UITextField!
    @IBOutlet weak var timeSlider: UISlider!
    @IBOutlet weak var timeText: UILabel!
    var quiz: Game!
    
    let continueToQuestionsSegueIdentifier = "continueToQuestions"
    
    let minimumTime = Float(1);
    let maximumTIme = Float(120);
    
    override func viewDidLoad() {
        let user = (UIApplication.sharedApplication().delegate as! AppDelegate).user
        self.quiz = Game(id: 0, name: "", creator: user, topic: "", timeLimit: 0, questions: [Question]())
        
        self.timeSlider.minimumValue = minimumTime
        self.timeSlider.maximumValue = maximumTIme
        self.timeSlider.value = 30
    }
    
    @IBAction func sliderValueChanged(sender: UISlider) {
        let seconds = Int(self.timeSlider.value)
        quiz.timeLimit = seconds
        self.timeText.text = "\(seconds) sekunder"
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == self.continueToQuestionsSegueIdentifier {
            self.quiz.name = quiznameText.text
            self.quiz.topic = topicText.text
            
            let questionsVC = segue.destinationViewController as! QuestionsViewController
            questionsVC.quiz = self.quiz;
        }
    }
}