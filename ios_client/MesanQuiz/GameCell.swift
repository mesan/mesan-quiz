import Foundation
import UIKit

class GameCell: UITableViewCell {
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var authorImage: UIImageView!
    @IBOutlet weak var authorName: UILabel!
    @IBOutlet weak var subject: UILabel!
    @IBOutlet weak var numberOfPlayers: UILabel!
    
    var game: Game?
    var imageService: ImageService

    required init(coder aDecoder: NSCoder) {
        imageService = ImageService()
        super.init(coder:aDecoder)
    }
    
    func setGame(game: Game) {
        self.game = game
        
        imageService.getImage(game.creator.profileImageUrl)
        title.text = game.name.uppercaseString
        authorName.text = game.creator.fullName
        subject.text = game.topic
        
        // TODO: Game does not currently contain number of players
        numberOfPlayers.text = nil

        initObservers()
    }
    
    func initObservers() {
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: "handleResult:",
            name: ImageService.GET_IMAGE_SUCCESS,
            object: nil)
    }
    
    func handleResult(notification: NSNotification) {
        if let
            userInfo    = notification.userInfo as? [String: AnyObject],
            image       = userInfo[ImageService.IMAGE_KEY] as? UIImage,
            shortName   = userInfo[ImageService.SHORTNAME_KEY] as? String
        {
            dispatch_async(dispatch_get_main_queue(), {
                if self.game?.creator.shortName == shortName {
                    self.authorImage.image = image
                }                
            })
        }
    }
}
