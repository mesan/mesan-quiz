//
//  GameService.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class GameService {
    static let PATH = "/games"
    
    static let GET_GAME_SUCCESS = "GetGameSuccess"
    static let GAME_KEY = "game"
    
    /*class func getAllGames() -> [Game] {
        var games: [Game] = []
        
        let person = Person(fullName: "Tore Brandtzæg", shortName: "toreb", profileImageUrl: "www.url.no/toreb")
        games.append(Game(id: 1, name: "TestQuiz", creator: person, topic: "Mesan Quiz ios utvikling", timeLimit: 30))
        
        return games
    }*/
    
    class func getGame(id: Int64) {
        let path = "\(PATH)/\(id)"
        var request = Service.makeRequest(path, HTTPMethod: "GET")
        
        var err: NSError?
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue(), completionHandler: handleResult)
    }
    
    class func handleResult(response:NSURLResponse!, data: NSData!, error: NSError!) {
        var error: AutoreleasingUnsafeMutablePointer<NSError?> = nil
        if let jsonResult = NSJSONSerialization.JSONObjectWithData(data, options:NSJSONReadingOptions.MutableContainers, error: error) as? NSDictionary {
            let game = Game.fromJson(jsonResult)
            println(game!)
            
            NSNotificationCenter.defaultCenter().postNotificationName(GET_GAME_SUCCESS, object: nil, userInfo: [GAME_KEY: game!])
        }
    }
}