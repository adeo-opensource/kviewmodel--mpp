//
//  Observable + Generic.swift
//  kviewmodel-swiftui
//
//  Created by Алексей Гладков on 07.06.2022.
//

import Foundation
import Combine
import SwiftUI

private class ObservableModel<StateObserved>: ObservableObject {
    @Published var stateObserved: StateObserved?
    
    init(statePublisher: AnyPublisher<StateObserved, Never>) {
        statePublisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$stateObserved)
    }
}

public struct ObservingView<StateObserved, Content>: View where Content: View {
    @ObservedObject private var model: ObservableModel<StateObserved>
    
    private let content: (StateObserved) -> Content
    
    public init(statePublisher: AnyPublisher<StateObserved, Never>, @ViewBuilder content: @escaping (StateObserved) -> Content) {
        self.model = ObservableModel(statePublisher: statePublisher)
        self.content = content
    }
    
    public var body: some View {
        let view: AnyView
        if let viewState = self.model.stateObserved {
            view = AnyView(content(viewState))
        } else {
            view = AnyView(EmptyView())
        }
        
        return view
    }
}
