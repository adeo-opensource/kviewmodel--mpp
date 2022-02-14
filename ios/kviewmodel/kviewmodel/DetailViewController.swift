//
//  DetailViewController.swift
//  kviewmodel
//
//  Created by Алексей Гладков on 14.02.2022.
//

import UIKit
import KViewModelShared

class DetailViewController: UIViewController {
    
    private let detailViewModel = DetailViewModel()
    
    private lazy var titleView: UILabel = {
        let titleView = UILabel()
        return titleView
    }()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        renderUI()
        
        detailViewModel.viewStates().watch { [weak self] viewState in
            guard let self = self, let viewState = viewState else { return }
            
            self.titleView.text = viewState.testData
        }
        
        detailViewModel.obtainEvent(viewEvent: .Launch())
    }
    
    deinit {
        print("Detail view controller de inited")
    }
    
    private func renderUI() {
        view.backgroundColor = .white
        view.addSubview(titleView)
        
        titleView.translatesAutoresizingMaskIntoConstraints = false
        titleView.leftAnchor.constraint(equalTo: view.leftAnchor, constant: 16).isActive = true
        titleView.topAnchor.constraint(equalTo: view.topAnchor, constant: 16).isActive = true
        titleView.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -16).isActive = true
    }
}
