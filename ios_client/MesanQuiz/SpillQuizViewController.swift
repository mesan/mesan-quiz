//
//  SpillQuizViewController.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 04.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import UIKit

class SpillQuizViewController: UIViewController {

    let game = GameService.getAllGames()[0]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = game.name
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

