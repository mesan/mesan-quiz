//
//  GameService.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class GameService {
    
    class func getAllGames() -> [Game] {
        var games: [Game] = []
    
        let person = Person(fullName: "Tore Brandtzæg", shortName: "toreb", profileImageUrl: "www.url.no/toreb")
        games.append(Game(id: 1, name: "TestQuiz", numberOfQuestions: 5, creator: person, topic: "Mesan Quiz ios utvikling", timeLimit: 30))
        
        return games
    }
}