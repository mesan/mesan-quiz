//
//  SpillQuizViewController.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 04.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import UIKit

class SpillQuizViewController: UIViewController {
    
    @IBOutlet weak var questionNumber: UILabel!
    @IBOutlet weak var questionText: UITextView!
    
    let game = GameService.getAllGames()[0]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = self.game.name
        self.questionText.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse finibus dui ut gravida dictum. Donec interdum luctus quam, sed porta erat elementum in. Donec pulvinar sodales dui id tincidunt. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed a maximus sem. Donec tempor, diam tincidunt laoreet auctor, urna nunc pulvinar diam, a malesuada mi augue at quam. Donec malesuada libero id sem mollis auctor vitae et lorem. Donec id viverra nisi, eget aliquam enim. Praesent sit amet nisi dui. Integer imperdiet venenatis tortor, et convallis ligula tincidunt vitae. Integer finibus quam quis quam congue scelerisque. Proin tincidunt eu tellus nec semper. Morbi turpis tortor, convallis quis euismod in, gravida in orci. Proin molestie sagittis bibendum. Vestibulum non feugiat diam, vestibulum varius diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

