import UIKit
import KViewModelShared

class ViewController: UIViewController {
    
    private let testViewModel = TestViewModel()
    
    private lazy var titleView: UILabel = {
        let titleView = UILabel()
        return titleView
    }()
    
    private lazy var counterView: UILabel = {
        let counterView = UILabel()
        counterView.font = .systemFont(ofSize: 26)
        return counterView
    }()
    
    private lazy var decrementButtonView: UIButton = {
        let buttonView = UIButton()
        buttonView.setTitle("-", for: .normal)
        buttonView.setTitleColor(.blue, for: .normal)
        buttonView.addTarget(self, action: #selector(decrementTap), for: .touchUpInside)
        return buttonView
    }()
    
    private lazy var incrementButtonView: UIButton = {
        let buttonView = UIButton()
        buttonView.setTitle("+", for: .normal)
        buttonView.setTitleColor(.blue, for: .normal)
        buttonView.addTarget(self, action: #selector(incrementTap), for: .touchUpInside)
        return buttonView
    }()
    
    private lazy var detailButtonView: UIButton = {
        let buttonView = UIButton()
        buttonView.setTitle("Open Detail", for: .normal)
        buttonView.setTitleColor(.blue, for: .normal)
        buttonView.addTarget(self, action: #selector(buttonTap), for: .touchUpInside)
        return buttonView
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        renderUI()
        
        testViewModel.viewStates().watch { [weak self] viewState in
            guard let self = self else { return }
            
            self.titleView.text = viewState.titleText
            self.counterView.text = String(viewState.counter)
        }
        
        testViewModel.viewActions().watch { [weak self] viewAction in
            guard let self = self, let viewAction = viewAction else { return }
            
            switch viewAction {
                case let args as TestAction.OpenDetail: self.presentDetail(param: args.param)
                default: break
            }
        }
    }
    
    private func renderUI() {
        view.addSubview(titleView)
        view.addSubview(counterView)
        view.addSubview(incrementButtonView)
        view.addSubview(decrementButtonView)
        view.addSubview(detailButtonView)
        
        titleView.translatesAutoresizingMaskIntoConstraints = false
        titleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 16).isActive = true
        titleView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        
        counterView.translatesAutoresizingMaskIntoConstraints = false
        counterView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        counterView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        
        decrementButtonView.translatesAutoresizingMaskIntoConstraints = false
        decrementButtonView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        decrementButtonView.rightAnchor.constraint(equalTo: counterView.leftAnchor, constant: -16).isActive = true
        
        incrementButtonView.translatesAutoresizingMaskIntoConstraints = false
        incrementButtonView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        incrementButtonView.leftAnchor.constraint(equalTo: counterView.rightAnchor, constant: 16).isActive = true
        
        detailButtonView.translatesAutoresizingMaskIntoConstraints = false
        detailButtonView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        detailButtonView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -16).isActive = true
    }
    
    private func presentDetail(param: Int32) {
        let detailViewController = DetailViewController()
        detailViewController.param = param
        present(detailViewController, animated: true, completion: nil)
    }
    
    @objc private func decrementTap() {
        testViewModel.obtainEvent(viewEvent: TestEvent.DecrementClick())
    }
    
    @objc private func incrementTap() {
        testViewModel.obtainEvent(viewEvent: TestEvent.IncrementClick())
    }
    
    @objc private func buttonTap() {
        testViewModel.obtainEvent(viewEvent: TestEvent.DetailClick())
    }
    
}

