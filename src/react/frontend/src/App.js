import './App.scss';
import { TeamPage } from './pages/TeamPage';
import {
  BrowserRouter,
  Route,
  Routes,
} from "react-router-dom";
import { MatchPage } from './pages/MatchPage';

function App() {
  return (
    <div className="App">
      <h1>IPL Dashboard</h1>
      <BrowserRouter>
        <Routes>
          <Route path="/teams/:teamName" element={<TeamPage />} />
          <Route path="/teams/:teamName/matches/:year" element={<MatchPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
