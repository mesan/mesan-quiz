//
//  Game.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Game {
    var id: Int
    var name: String
    var numberOfQuestions: Int
    var creator: Person
    var topic: String
    var timeLimit: Int
    
    init(id: Int, name: String, numberOfQuestions: Int, creator: Person, topic: String, timeLimit: Int) {
        self.id = id
        self.name = name
        self.numberOfQuestions = numberOfQuestions
        self.creator = creator
        self.topic = topic
        self.timeLimit = timeLimit
    }
}