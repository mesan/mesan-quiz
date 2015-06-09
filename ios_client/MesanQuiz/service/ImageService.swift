import Foundation
import UIKit

class ImageService {
    
    static let GET_IMAGE_SUCCESS = "GetImageSuccess"
    static let IMAGE_KEY = "ImageKey"
    static let SHORTNAME_KEY = "ShortnameKey"
    
    var shortName: String?
    
    func getImage(imgURL: String) {
        let imageFileName = split(imgURL) { $0 == "/"}.last
        if imageFileName != nil {
            shortName = split(imageFileName!) { $0 == "."}.first
        }
        
        // Download an NSData representation of the image at the URL
        let request: NSURLRequest = NSURLRequest(URL: NSURL(string: imgURL)!)
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue.mainQueue(), completionHandler: handleResult)
    }
    
    func handleResult(response: NSURLResponse!, data: NSData!, error: NSError!) {
        if error == nil {
            let image = UIImage(data: data)
            
            if image != nil {
              NSNotificationCenter.defaultCenter().postNotificationName(ImageService.GET_IMAGE_SUCCESS, object: nil, userInfo: [ImageService.IMAGE_KEY: image!, ImageService.SHORTNAME_KEY: shortName!])
            }
        }
        else {
            println("Error: \(error.localizedDescription)")
        }
    }

}