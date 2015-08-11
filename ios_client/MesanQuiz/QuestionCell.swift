import UIKit

class QuestionCell: UITableViewCell {
 
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var alternative1Label: UILabel!
    @IBOutlet weak var alternative2Label: UILabel!
    @IBOutlet weak var alternative3Label: UILabel!
    @IBOutlet weak var alternative4Label: UILabel!
    
    var question: Question!
    
    func setQuestion(question: Question) {
        self.question = question
        questionLabel.text = question.question!
        setAlternativeText(alternative1Label, alternativeNumber: 1)
        setAlternativeText(alternative2Label, alternativeNumber: 2)
        setAlternativeText(alternative3Label, alternativeNumber: 3)
        setAlternativeText(alternative4Label, alternativeNumber: 4)
    }
    
    func setAlternativeText(label: UILabel, alternativeNumber: Int) {
        let alternative = self.question.alternatives![alternativeNumber - 1]
        label.text = "\(alternativeNumber)) \(alternative.alternative!)"
        label.textColor = alternative.answer! ? UIColor.mesanQuizGreenColor() : UIColor.blackColor()
    }
}
