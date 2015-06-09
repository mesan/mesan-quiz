import Foundation
import UIKit

class GameCell: UITableViewCell {
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var authorImage: UIImageView!
    @IBOutlet weak var authorName: UILabel!
    @IBOutlet weak var subject: UILabel!
    @IBOutlet weak var numberOfPlayers: UILabel!
    
    func setGame(game: Game) {
        title.text = game.name
        // TODO
        // authorImage =
        authorName.text = game.creator.fullName
        subject.text = game.topic
    }
}
