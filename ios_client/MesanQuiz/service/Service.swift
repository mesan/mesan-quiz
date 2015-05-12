//
//  Service.swift
//  MesanQuiz
//
//  Created by Anders Ullnæss on 5/12/15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation


class Service {
    static let BASE_URL = "https://warm-earth-6928.herokuapp.com"
    
    class func makeRequest(path: String, HTTPMethod: String/*, withAuthentication: Bool*/) -> NSMutableURLRequest {
        var url = BASE_URL + path
        println(url)
        var request = NSMutableURLRequest()
        request.URL = NSURL(string: url)
        request.HTTPMethod = HTTPMethod
        
        /*if withAuthentication {
        var token = NSUserDefaults.standardUserDefaults().objectForKey("token") as? String
        request.setValue(token, forHTTPHeaderField: "Authorization")
        }*/
        return request
    }
    

}
