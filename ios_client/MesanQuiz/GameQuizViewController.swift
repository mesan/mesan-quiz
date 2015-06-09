//
//  GameQuizViewController.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 04.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import UIKit

class GameQuizViewController: UIViewController {
    
    @IBOutlet weak var questionNumber: UILabel!
    @IBOutlet weak var questionText: UITextView!
    @IBOutlet weak var labelTimeLeft: UILabel!
    
    @IBOutlet weak var btnAlternative1: UIButton!
    @IBOutlet weak var btnAlternative2: UIButton!
    @IBOutlet weak var btnAlternative3: UIButton!
    @IBOutlet weak var btnAlternative4: UIButton!
    
    var buttons: [UIButton] = []
    var game: Game!
    var currentQuestionIndex: Int = 0
    var countDownTimeLeft = 0
    var countDownTimer: NSTimer!
    var correctAnswers = 0
    
    let nextQuestionDelay = 1.0
    
    func fixNavigationBar() {
        self.navigationController?.navigationBar.translucent = false
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: "handleResult:",
            name: GameService.GET_GAME_SUCCESS,
            object: nil)
        
        buttons = [btnAlternative1, btnAlternative2, btnAlternative3, btnAlternative4]
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        startGame()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func startGame() {
        self.title = game.name
        self.correctAnswers = 0
        self.goToQuestion(0, delay: 0)
    }
    
    func goToQuestion(questionIndex: Int, delay: NSTimeInterval) {
        if questionIndex >= self.game.questions!.count {
            println("Game complete! \(self.correctAnswers) correct answers")
            return // TODO: Game complete
        }
        
        self.currentQuestionIndex = questionIndex
        
        let delayTime = dispatch_time(DISPATCH_TIME_NOW, Int64(delay * Double(NSEC_PER_SEC)))
        dispatch_after(delayTime, dispatch_get_main_queue()) {
            self.initView(self.game.questions?[self.currentQuestionIndex])
        }
    }
    
    func initView(question: Question!) {
        if (question == nil) {
            return
        }
        
        self.labelTimeLeft.textColor = UIColor.blackColor()
        self.labelTimeLeft.text = nil

        self.questionNumber.text = "\(self.currentQuestionIndex + 1) / \(game.questions!.count)"
        self.questionText.text = question.question
        
        if let alternatives = question.alternatives {
            var i = 0
            for ; i < alternatives.count; i++ {
                var button = self.buttons[i]
                button.hidden = false
                button.enabled = true
                button.backgroundColor = UIColor.whiteColor()
                button.setTitle(alternatives[i].alternative, forState: UIControlState.Normal)
            }
            
            // Hides the rest of the buttons if there are less than 4 alternatives
            for index in i...(self.buttons.count - 1) {
                self.buttons[index].hidden = true
            }
        }
        
        self.countDownTimeLeft = game.timeLimit
        self.setTimer(NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: Selector("countDown:"), userInfo: nil, repeats: true))
    }
    
    func setTimer(timer: NSTimer?) {
        if self.countDownTimer != nil && self.countDownTimer.valid {
            self.countDownTimer.invalidate()
        }
        
        self.countDownTimer = timer
    }
    
    func countDown(timer: NSTimer) {
        self.countDownTimeLeft -= 1
        self.labelTimeLeft.text = "\(self.countDownTimeLeft) sekunder igjen!"
        self.labelTimeLeft.textColor = UIColor.blackColor()
        
        if self.countDownTimeLeft == 0 {
            setTimer(nil)
            
            dispatch_async(dispatch_get_main_queue(), {
                self.disableButtons()
                self.labelTimeLeft.text = "Tiden er ute!"
                self.labelTimeLeft.textColor = UIColor.redColor()
                self.goToQuestion(self.currentQuestionIndex + 1, delay: self.nextQuestionDelay)
            })
        }
    }

    @IBAction func chooseAlternative(sender: UIButton) {
        if self.game.questions!.isEmpty {
            return
        }
        
        if let question = self.game.questions?[self.currentQuestionIndex] {
            if let alternative = question.alternatives?.filter({ $0.alternative! == sender.titleLabel?.text }).first {
                println(alternative.alternative)
                
                if alternative.answer! {
                    correctAnswers++
                    sender.backgroundColor = UIColor.greenColor()
                } else {
                    sender.backgroundColor = UIColor.redColor()
                }
                
                sender.backgroundColor = alternative.answer! ? UIColor.greenColor() : UIColor.redColor()
                self.setTimer(nil)
                
                self.disableButtons()
                self.goToQuestion(self.currentQuestionIndex + 1, delay: self.nextQuestionDelay)
            }
        }
    }
    
    func disableButtons() {
        for button in self.buttons {
            button.enabled = false
        }
    }
}

