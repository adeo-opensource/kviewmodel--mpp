import UIKit
import KViewModelShared

class DetailViewController: UIViewController {
    
    var param: Int32!
    
    private lazy var detailViewModel = DetailViewModel(param: param)
    
    private lazy var titleView: UILabel = {
        let titleView = UILabel()
        return titleView
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        renderUI()
        
        detailViewModel.viewStates().watch { [weak self] viewState in
            guard let self = self else { return }
            
            self.titleView.text = viewState.text
        }
    }
    
    deinit {
        print("Detail view controller deinited")
    }
    
    private func renderUI() {
        view.backgroundColor = .white
        view.addSubview(titleView)
        
        titleView.translatesAutoresizingMaskIntoConstraints = false
        titleView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        titleView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
    }
}
