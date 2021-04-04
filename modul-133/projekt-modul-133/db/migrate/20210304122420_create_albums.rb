class CreateAlbums < ActiveRecord::Migration[6.0]
  def change
    create_table :albums do |t|
      t.string :band
      t.string :title
      t.integer :year
      t.integer :no_songs
      t.integer :duration
      t.timestamps
    end
  end
end
