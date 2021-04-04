class Album < ApplicationRecord
    has_many :songs, dependent: :restrict_with_error
    validates_presence_of :title, message: "darf nicht leer sein"
    validates_presence_of :band, message: "darf nicht leer sein"
    validates_numericality_of :year, :message => "darf nur eine Zahl sein"
end
