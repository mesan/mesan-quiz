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
    @IBOutlet weak var labelTimeLeft: UILabel!
    
    @IBOutlet weak var btnAlternative1: UIButton!
    @IBOutlet weak var btnAlternative2: UIButton!
    @IBOutlet weak var btnAlternative3: UIButton!
    @IBOutlet weak var btnAlternative4: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: "handleResult:",
            name: GameService.GET_GAME_SUCCESS,
            object: nil)
        
        GameService.getGame(1)
    }
    
    @objc func handleResult(notification: NSNotification) {
        if let
            userInfo    = notification.userInfo as? [String: Game],
            game        = userInfo[GameService.GAME_KEY] 
        {
            self.title = game.name
        }
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

