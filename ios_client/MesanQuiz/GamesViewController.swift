import Foundation
import UIKit

class GamesViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    let cellIdentifier = "GameCell"
    let segueIdentifier = "GameSegue"
    
    var games: [Game] = []
    
    var gameService: GameService!
    
    var gameQuizViewController: GameQuizViewController!
    
    override func viewDidLoad() {
        initObservers()

        gameService = GameService()
        gameService.getGames()
    }
    
    func initObservers() {
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: "handleResult:",
            name: GameService.GET_GAMES_SUCCESS,
            object: nil)
    }
    
    func handleResult(notification: NSNotification) {
        if let
            userInfo    = notification.userInfo as? [String: [Game]],
            games       = userInfo[GameService.GAMES_KEY]
        {
            dispatch_async(dispatch_get_main_queue(), {
                self.games = games
                self.tableView.reloadData()
            })
        }
    }
    
    func getGames() {
        let priority = DISPATCH_QUEUE_PRIORITY_DEFAULT
        dispatch_async(dispatch_get_global_queue(priority, 0)) {
            self.gameService.getGames()
        }
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return games.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! GameCell
        
        var game = games[indexPath.row]
        cell.setGame(game)
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        gameQuizViewController.game = games[indexPath.row]
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == segueIdentifier {
            if let destinationVC = segue.destinationViewController as? GameQuizViewController {
                gameQuizViewController = destinationVC
            }
        }
    }
}