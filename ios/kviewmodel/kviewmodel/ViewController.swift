//
//  ViewController.swift
//  kviewmodel
//
//  Created by Алексей Гладков on 14.02.2022.
//

import UIKit
import KViewModelShared

class ViewController: UIViewController {
    
    private let testViewModel = TestViewModel()
    
    private lazy var titleView: UILabel = {
        let titleView = UILabel()
        return titleView
    }()
    
    private lazy var buttonView: UIButton = {
        let buttonView = UIButton()
        buttonView.setTitle("Open Detail", for: .normal)
        buttonView.setTitleColor(.blue, for: .normal)
        buttonView.addTarget(self, action: #selector(buttonTap), for: .touchUpInside)
        return buttonView
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        renderUI()
        
        testViewModel.viewStates().watch { [weak self] viewState in
            guard let self = self, let viewState = viewState else { return }
            
            self.titleView.text = viewState.someText
        }
        
        testViewModel.viewActions().watch { [weak self] viewAction in
            guard let self = self, let viewAction = viewAction else { return }
            
            switch viewAction {
            case _ as TestAction.OpenDetail:
                self.presentDetail()
                
            default:
                break
            }
        }
        
        testViewModel.obtainEvent(viewEvent: TestEvent.Launch())
    }
    
    private func renderUI() {
        view.addSubview(titleView)
        view.addSubview(buttonView)
        
        titleView.translatesAutoresizingMaskIntoConstraints = false
        titleView.leftAnchor.constraint(equalTo: view.leftAnchor, constant: 16).isActive = true
        titleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 16).isActive = true
        titleView.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -16).isActive = true
        
        buttonView.translatesAutoresizingMaskIntoConstraints = false
        buttonView.leftAnchor.constraint(equalTo: view.leftAnchor, constant: 16).isActive = true
        buttonView.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -16).isActive = true
        buttonView.topAnchor.constraint(equalTo: titleView.bottomAnchor, constant: 16).isActive = true
        buttonView.heightAnchor.constraint(equalToConstant: 48).isActive = true
    }
    
    private func presentDetail() {
        let detailViewController = DetailViewController()
        present(detailViewController, animated: true, completion: nil)
    }
    
    @objc private func buttonTap() {
        testViewModel.obtainEvent(viewEvent: TestEvent.OpenDetail())
    }
}

