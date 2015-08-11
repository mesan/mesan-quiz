import UIKit

class CreateQuestionViewController: UIViewController {

    let saveQuestionSegueIdentifier = "saveQuestionSegue"
    
    var question: Question!
    
    @IBOutlet weak var questionTextView: UITextView!
    @IBOutlet weak var subjectTextField: UITextField!
    @IBOutlet weak var alternative1TextField: UITextField!
    @IBOutlet weak var alternative2TextField: UITextField!
    @IBOutlet weak var alternative3TextField: UITextField!
    @IBOutlet weak var alternative4TextField: UITextField!
    @IBOutlet weak var correctAlternativeSelector: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func saveQuestion(sender: AnyObject) {
        var newQuestion = Question()
        newQuestion.question = self.questionTextView.text
        newQuestion.subject = self.subjectTextField.text
        
        let correctAlternative = correctAlternativeSelector.selectedSegmentIndex
        var alternatives = [Alternative]()
        
        alternatives.append(Alternative(alternative: alternative1TextField.text, answer: false))
        alternatives.append(Alternative(alternative: alternative2TextField.text, answer: false))
        alternatives.append(Alternative(alternative: alternative3TextField.text, answer: false))
        alternatives.append(Alternative(alternative: alternative4TextField.text, answer: false))
        alternatives[correctAlternative].answer = true
        newQuestion.alternatives = alternatives
        
        self.question = newQuestion
        
        self.performSegueWithIdentifier(saveQuestionSegueIdentifier, sender: self)
    }

}
