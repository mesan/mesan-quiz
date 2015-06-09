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
    
    var game: Game!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = game.name
    }
    
    func fixNavigationBar() {
        self.navigationController?.navigationBar.translucent = false
    }
}

