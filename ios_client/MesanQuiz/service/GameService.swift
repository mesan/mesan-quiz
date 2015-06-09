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
    
    static let GET_GAMES_SUCCESS = "GetGamesSuccess"
    static let GAMES_KEY = "games"
    
    
    func getGames() {
        var request = Service.makeRequest(GameService.PATH, HTTPMethod: "GET")

        var error: NSError?
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue(), completionHandler: handleGamesResult)
    }
    
    func handleGamesResult(response: NSURLResponse!, data: NSData!, error: NSError!) {
        var error: AutoreleasingUnsafeMutablePointer<NSError?> = nil
        if let jsonResult = NSJSONSerialization.JSONObjectWithData(data, options:NSJSONReadingOptions.MutableContainers, error: error) as? NSArray {
            let games = Game.fromJsonArray(jsonResult)
            println(games)
            
            NSNotificationCenter.defaultCenter().postNotificationName(GameService.GET_GAMES_SUCCESS, object: nil, userInfo: [GameService.GAMES_KEY: games!])
        }
    }
    
    func getGame(id: Int64) {
        let path = "\(GameService.PATH)/\(id)"
        var request = Service.makeRequest(path, HTTPMethod: "GET")
        
        var err: NSError?
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue(), completionHandler: handleGameResult)
    }
    
    func handleGameResult(response: NSURLResponse!, data: NSData!, error: NSError!) {
        var error: AutoreleasingUnsafeMutablePointer<NSError?> = nil
        if let jsonResult = NSJSONSerialization.JSONObjectWithData(data, options:NSJSONReadingOptions.MutableContainers, error: error) as? NSDictionary {
            let game = Game.fromJson(jsonResult)
            println(game!)
            
            NSNotificationCenter.defaultCenter().postNotificationName(GameService.GET_GAME_SUCCESS, object: nil, userInfo: [GameService.GAME_KEY: game!])
        }
    }
}